<%@ include file="include/header.jsp"%>
<body>
<div id="site">
    <%@ include file="include/menu.jsp"%>
    <div id="conteneur">
        <%@ include file="include/bandeaudroite.jsp"%>

        <div id="contenu">
            <H1>Modification d'une oeuvre</H1>

            <DIV align="center">
                <FORM name='identification' method="post"
                      action="Controleur?action=modificationOeuvre"
                      onsubmit="return teste()">
                    <P align="left">
                         <BR>Titre de l'oeuvre :
                            <INPUT type="text" name="txttitre"  value=${uneOeuvre.titreOeuvrevente} id="titre">
                        <BR>
                         <BR>Etat de l'oeuvre ( L pour Libre et R pour Reserve ):
                            <INPUT type="text" name="txtetat"  value=${uneOeuvre.etatOeuvrevente} id="etat">
                        <BR>
                        <BR> Prix de l'oeuvre :
                            <INPUT type="text" name="txtprix" value=${uneOeuvre.prixOeuvrevente} id="prix">
                        <BR>

                        <!-- Boutons Ajouter -->
                        <BR>
                        <button type="submit" name="id" value=${uneOeuvre.idOeuvrevente}> MODIFIER </button>
                        <BR>
                    </P>
                </FORM>
                <P>  <BR>  <BR> <BR> </P>
            </div>
            <%@ include file="include/footer.jsp"%>
        </div>
    </div>
</body>
</html>
