/**
 * Created by lingqianxie on 12/6/15.
 */

// Userlist data array for filling in info box
var ClientListData = [];

// DOM Ready =============================================================
$(document).ready(function() {

    // Populate the user table on initial page load
    populateTable();
    var CL = $('#ClientList');
    CL.find('table').find('tbody').on('click', 'td a.LinkShowClient', showClassInfo);
    CL.find('table').find('tbody').on('click', 'td a.LinkStartObserve', startobserve);
    CL.find('table').find('tbody').on('click', 'td a.LinkEndObserve', endobserve);
});

// Functions =============================================================

// Fill table with data
function populateTable() {

    // Empty content string
    var tableContent = '';

    // jQuery AJAX call for JSON
    $.getJSON( '/server/Clientlist', function( data ) {
        ClientListData = data;
        // For each item in our JSON, add a table row and cells to the content string
        $.each(data, function(){
            tableContent += '<tr>';
            tableContent += '<td><a href="#" class="LinkShowClient" rel="' + this.ID + '">' + this.ID+'</a></td>';
            tableContent += '<td>' + this.ObserveState + '</td>';
            tableContent += '<td><a href="#" class="LinkStartFeature" rel="' + this.ID + '">On</a></td>';
            tableContent += '<td><a href="#" class="LinkEndObserve" rel="' + this.ID + '">Off</a></td>';
            tableContent += '</tr>';
        });

        // Inject the whole content string into our existing HTML table
        $('#ClientList').find('table').find('tbody').html(tableContent);
    });
}

function showClassInfo(event){
    event.preventDefault();

    var thisClientId = $(this).attr('rel');

    var arrayPosition = ClientListData.map(function(arrayItem){return arrayItem.ID;}).indexOf(thisClientId);

    var thisClientObject = ClientListData[arrayPosition];

    //Populate Info Box
    $('#ClientInfoManufacture').text(thisClientObject.manufacture);
    $('#ClientInfoModel').text(thisClientObject.model);
    $('#ClientInfoVIN').text(thisClientObject.VIN);
    $('#ClientInfoYear').text(thisClientObject.year);
    $('#ClientInfoOwner').text(thisClientObject.owner);
}

function startobserve(event){
    event.preventDefault();

    $.ajax({
        type:'POST',
        url: '/server/observe/' + $(this).attr('rel')
    }).done(function(response){

        //check for a successful (blank) response
        if (response.msg === ''){
        }else{
            alert('Error: ' + response.msg);
        }

        //update the table
        populateTable();
    })
}

function endobserve(event){

    event.preventDefault();

    $.ajax({
        type:'POST',
        url: '/server/cancelobserve/' + $(this).attr('rel')
    }).done(function(response){

        //check for a successful (blank) response
        if (response.msg === ''){
        }else{
            alert('Error: ' + response.msg);
        }

        //update the table
        populateTable();
    })
}