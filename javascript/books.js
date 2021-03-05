'use strict'

function Book(bookId, title, authorName) {
    this.bookId = bookId;
    this.title = title;
    this.authorName = authorName;
}

var books = [ new Book(0, "Frankensein", "Mary Shelley"),
              new Book(1, "Journey to the Center of the Earth", "Jules Verne"),
              new Book(2, "The Time Machine", "H.G. Wells"),
              new Book(3, "The War of the Worlds", "H.G. Wells"),
              new Book(4, "Brave New World", "Aldous Huxley"),
              new Book(5, "Nineteen Eighty-Four", "George Orwell"),
              new Book(6, "The Martian Chronicles", "Ray Bradbury"),
              new Book(7, "I, Robot", "Isaac Asimov"),
              new Book(8, "Foundation", "Isaac Asimov"),
              new Book(9, "Fahrenheit 451", "Ray Bradbury"),
              new Book(10, "Childhood's End", "Arthur C. Clarke"),
              new Book(11, "I Am Legend", "Richard Matheson"),
              new Book(12, "The Chrysalids", "John Wyndham"),
              new Book(13, "Solaris", "Stanislaw Lem"),
              new Book(14, "Stranger in a Strange Land", "Robert A. Heinlein"),
              new Book(15, "A Wrinkle in Time", "Madeleine L'Engle"),
              new Book(16, "The Man in the High Castle", "Philip K. Dick"),
              new Book(17, "2001: A Space Odyssey", "Arthur C. Clarke"),
              new Book(18, "The Hitchhiker's Guide to the Galaxy", "Douglas Adams"),
              new Book(19, "Contact", "Carl Sagan")];

var current_page = 0;
var selected_column = 0;
var first_column_page = 1;
var max_pages = Math.trunc(books.length/6);
if (books.length-(6*max_pages) > 0)
{
    max_pages++;
}

function displayPage(num) {
    if (num < 0 && selected_column > 0)
    {   
        selected_column--; 
    }
    else if (num < 0 && selected_column == 0 && first_column_page > 1)
    {
        first_column_page--;
        document.getElementById("page_1").innerHTML = first_column_page;
        document.getElementById("page_2").innerHTML = first_column_page+1;
        document.getElementById("page_3").innerHTML = first_column_page+2;
    }
    else if (num < 0)
    {
        return;
    }

    if (num == 3 && selected_column < 2)
    {
        selected_column++; 
    }
    else if (num == 3 && first_column_page+2 < max_pages)
    {
        first_column_page++;
        document.getElementById("page_1").innerHTML = first_column_page;
        document.getElementById("page_2").innerHTML = first_column_page+1;
        document.getElementById("page_3").innerHTML = first_column_page+2;
    }
    else if (num == 3)
    {
        return;
    }

    if (num >= 0 && num <= 2)
    {
        selected_column = num;
    }

    var last_index = (first_column_page+selected_column)*6;
    if (last_index >= books.length)
    {
        last_index = books.length-1;
    }
    for(var i = 1; i <= 6; i++)
    {
        var index = (((first_column_page-1)+selected_column)*6)+(i-1);
        if (index >= books.length)
        {
            document.getElementById("listing_"+i).innerHTML = "";
        }
        else
        {
            document.getElementById("listing_"+i).innerHTML = populateListing(index);
        }
    }
}

function populateListing(index)
{
    var listing = "<h2>"+books[index].title+"</h2>";
    listing += "<h3>by "+books[index].authorName+"</h3>";
    listing += "Book Id = "+books[index].bookId;
    return listing;
}