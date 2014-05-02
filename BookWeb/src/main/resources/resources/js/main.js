/**
 * Created by nazi on 11/25/13.
 */
var bookManager = {
    lastId: 1,
    data: [
        {
            id: 1,
            bookName: "mohakeme",
            author: "kafka",
            readStatus: 1
        }
    ],
    init: function () {
        var templateHtml = $('#bookTemplate').html();
        this.template = Handlebars.compile(templateHtml);
        this.showBooks();
        $('#add').click(this.openAddModal);
        $('#save').click(this.saveAddModal);
    },
    showBooks: function () {
        $("#bookContainer").html('');
        for (var i in this.data) {
            this.data[i].number = parseInt(i) + 1;
            $("#bookContainer").append(this.template(this.data[i]));
        }
    },
    addBook: function (book) {
        bookManager.data.push(book);

    },
    openAddModal: function () {
        $('#addModal').modal("show");

    },
    saveAddModal: function () {
        var book = new Object();
        book.bookName = $('#name').val();
        book.author = $('#author').val();
        book.id = ++bookManager.lastId;
        bookManager.addBook(book);
        bookManager.showBooks();
        $('#addModal').modal("hide");
    },

    editBook: function (book) {

    },
    delBook: function (id) {
        for (var i = 0; i < bookManager.data.length; i++) {
            if (bookManager.data[i].id == id) {
                bookManager.data.pop(bookManager.data[i]);
            }
        }
        bookManager.showBooks();
    }
};
bookManager.init();