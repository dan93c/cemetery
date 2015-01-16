<div id="banner"></div>
<ul id="menu">
	<li><a href="${CONTEXT_PATH}/history/1?action=0&sch=">Home</a></li>
	<li><a href="#">Parcele</a>
		<ul>
			<li><a href="${CONTEXT_PATH}/plot/add">Adauga parcela</a></li>
			<li><a href="${CONTEXT_PATH}/plot/list">Vizualizeaza lista
					parcele</a></li>
			<li><a href="${CONTEXT_PATH}/cemetery/add">Adauga cimitir</a></li>
			<li><a href="${CONTEXT_PATH}/cemetery/list">Vizualizeaza
					lista cimitire</a></li>
		</ul></li>
	<li><a href="#">Morminte</a>
		<ul>
			<li><a href="${CONTEXT_PATH}/grave/add">Adauga mormant</a></li>
			<%-- 			<li><a href="${CONTEXT_PATH}/grave/list">Vizualizare
					morminte</a></li> --%>
			<li><a href="${CONTEXT_PATH}/graveRequest/add">Adauga cerere
					de atribuire loc de inhumare</a>
			<li><a href="${CONTEXT_PATH}/grave/morminte/1?o=0&sch=">Registrul
					de morminte</a></li>
			<li><a
				href="${CONTEXT_PATH}/grave/morminte-monumente/1?o=0&sch=">Registrul
					de morminte - Monumente funerare</a></li>
			<li><a href="${CONTEXT_PATH}/graveRequest/cereri/1?o=0&sch=">Registrul
					cu evidenta cererilor de atribuire a locurilor de inhumare</a></li>
		</ul></li>
	<li><a href='#'>Decedati</a>
		<ul>
			<li><a href="${CONTEXT_PATH}/dead/add">Adauga decedat</a></li>
			<li><a href="${CONTEXT_PATH}/dead2/add">Adauga decedat fara
					apartinator</a></li>
			<li><a href="${CONTEXT_PATH}/dead/inmormantari/1?o=0&sch=">Registrul
					anual de programare a inmormantarilor</a></li>
			<li><a href="${CONTEXT_PATH}/dead/list/1?o=3&sch=">Registrul
					index anual al decedatilor</a></li>
			<li><a href="${CONTEXT_PATH}/dead2/list/1?o=0&sch=">Registrul
					index anual al decedatilor fara apartinatori</a></li>
		</ul></li>
	<li><a href='#'>Altele</a>
		<ul>
			<li><a href="${CONTEXT_PATH}/contract/add">Adauga contract
					de concesiune</a></li>
			<li><a href="${CONTEXT_PATH}/claim/add">Adauga reclamatie</a>
			<li><a href="${CONTEXT_PATH}/contract/list/1?o=0&sch=">Registrul
					anual de evidenta a contractelor de concesiune</a></li>
			<li><a href="${CONTEXT_PATH}/claim/reclamatii/1?o=0&sch=">Registrul
					anual cu evidenta sesizarilor si reclamatiile cetatenilor</a></li>
			<li><a href="${CONTEXT_PATH}/contract/expired/1?o=0&sch=">Lista
					mormintelor expirate</a></li>
		</ul></li>
</ul>
<!-- div containing the logout button and the user's name -->
<div id="logout_header">
	<p id="logged_user">&nbsp;${username}&nbsp;</p>
	<a href="${CONTEXT_PATH}/j_spring_security_logout"> <img
		id="logout_button"
		src="${CONTEXT_PATH}/resources/img/logout4.png"
		alt="logout" title="Log out"></a>
</div>