<div id="banner"></div>
<ul id="menu">
	<li><a href="#">Home</a></li>
	<li><a href="">Cimitire</a>
		<ul>
			<li><a href="${CONTEXT_PATH}/cemetery/add">Adauga</a></li>
			<li><a href="${CONTEXT_PATH}/cemetery/list">Vizualizeaza
					lista</a></li>
		</ul></li>
	<li><a href="#">Parcele</a>
		<ul>
			<li><a href="${CONTEXT_PATH}/plot/add">Adauga</a></li>
			<li><a href="${CONTEXT_PATH}/plot/list">Vizualizeaza lista</a></li>
		</ul></li>
	<li><a href="#">Morminte</a>
		<ul>
			<li><a href="${CONTEXT_PATH}/contract/add">Adauga contract</a></li>
			<li><a href="${CONTEXT_PATH}/contract/list">Vizualizare
					contracte</a></li>
			<li><a href="${CONTEXT_PATH}/grave/add">Adauga mormant</a></li>
			<li><a href="${CONTEXT_PATH}/grave/list">Vizualizare
					morminte</a></li>
			<li><a href="#">Contract expirat</a></li>
		</ul></li>
	<li><a href='#'>Decedati</a>
		<ul>
			<li><a href="${CONTEXT_PATH}/dead/add">Adauga decedat</a></li>
			<li><a href="${CONTEXT_PATH}/dead/list">Vizualizeaza
					decedati</a></li>
		</ul></li>
</ul>
<!-- div containing the logout button and the user's name -->
<div id="logout_header">
	<p id="logged_user">&nbsp;Logged User&nbsp;</p>
	<img id="logout_button"
		src="<%=request.getContextPath()%>/resources/img/logout3.png"
		alt="logout">
</div>