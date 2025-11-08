
<%@ include file="common/header.jspf"%>

<%@ include file="common/navigation.jspf"%>



<div class="card text-center" >
  <div class="card-body">
    <h5 class="card-title">Welcome ${name}</h5>
    <p class="card-text">This is the portal which will help you to manage your todos for everyday task</p>
    <div class="d-grid gap-2 col-6 mx-auto">
		<a href="/list-todos" class="btn btn-primary">Manage your Todos</a>
	</div>
  </div>
</div>



<%@ include file="common/footer.jspf"%>