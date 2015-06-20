<%@page import="mq.Database"%>
<%@page import="mq.MovieQuote"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MovieQuotes</title>
<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="bootstrap/css/bootstrap-theme.min.css" rel="stylesheet">
<!-- Custom styles -->
<link href="stylesheets/moviequotes.css" rel="stylesheet">
</head>
<body>
  <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">MovieQuotes</a>
      </div>
      <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav navbar-left">
          <li><a id="add-quote" data-toggle="modal" data-target="#insert-quote-modal" href="#">Add Quote</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li><a id="toggle-edit" href="#">Edit</a></li>
        </ul>
      </div>
      <!--/.nav-collapse -->
    </div>
  </div>
  <div class="container">
    <div class="table-wrapper">
      <table class="table table-striped">
        <thead>
          <tr>
            <th>Quote</th>
            <th>Movie</th>
            <th class="hidden edit-actions">Edits</th>
          </tr>
        </thead>
        <tbody>
        	<% for (MovieQuote mq : Database.getInstance().query()) { %>
            <tr>
              <td><%= mq.getQuote() %></td>
              <td><%= mq.getMovie() %></td>
              <td class="hidden edit-actions">
                <button data-toggle="modal" data-target="#insert-quote-modal" type="button" class="edit-movie-quote btn btn-xs btn-success">
                  <span class="glyphicon glyphicon-pencil"></span>
                  <div class="hidden id"><%= mq.getId() %></div>
                  <div class="hidden quote"><%= mq.getQuote() %></div>
                  <div class="hidden movie"><%= mq.getMovie() %></div>
                </button>
                <button data-toggle="modal" data-target="#delete-quote-modal" type="button" class="delete-movie-quote btn btn-xs btn-danger">
                  <span class="glyphicon glyphicon-remove"></span>
                  <div class="hidden id"><%= mq.getId() %></div>
                </button>
              </td>
            </tr>
            <% } %>
        </tbody>
      </table>
    </div>


    <!-- Modal -->
    <div id='insert-quote-modal' class="modal fade" tabindex="-1" role="dialog"
      aria-labelledby="Insert quote" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title">Add a MovieQuote</h4>
          </div>
          <form action="insertquote" method="POST" class="form-horizontal" role="form">
            <input name="id" type="text" class="hidden">
            <div class="modal-body">
              <div class="form-group">
                <label for="quote-input" class="col-sm-2 control-label">Quote:</label>
                <div class="col-sm-10">
                  <input id="quote-input" name="quote" type="text" class="form-control" placeholder="Quote">
                </div>
              </div>
              <div class="form-group">
                <label for="movie-input" class="col-sm-2 control-label">Movie: </label>
                <div class="col-sm-10">
                  <input id="movie-input" name="movie" type="text" class="form-control" placeholder="Title">
                </div>
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
              <button type="submit" class="btn btn-primary">Add Quote</button>
            </div>
          </form>
        </div>
        <!-- /.modal-content -->
      </div>
      <!-- /.modal-dialog -->
    </div>
    <!-- /.modal -->


  <!-- Modal -->
  <div id='delete-quote-modal' class="modal fade" tabindex="-1" role="dialog" aria-labelledby="Delete MovieQuote" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h4 class="modal-title">Delete MovieQuote</h4>
        </div>
        <form class="form-horizontal" action="deletequote" method="POST">
          <input type="hidden" name="id">
          <div class="modal-body">
            <div>Are you sure you wish to delete this quote?</div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
            <button type="submit" class="btn btn-danger">Delete quote</button>
          </div>
        </form>
      </div>
      <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
  </div>
  <!-- /.modal -->


  </div>
  <!-- /.container -->
  <!-- Bootstrap core JavaScript
    ================================================== -->
  <!-- Placed at the end of the document so the pages load faster -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>
  <script src="js/moviequotes.js"></script>
</body>
</html>
