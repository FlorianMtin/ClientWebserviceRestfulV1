<%@ include file="include/header.jsp"%>
<body>
<div id="site">
    <%@ include file="include/menu.jsp"%>
    <div id="conteneur">
        <%@ include file="include/bandeaudroite.jsp"%>

        <div id="contenu">
            <H1>Ajout d'une Oeuvre</H1>

            <DIV align="center">
                <FORM name='identification' method="post"
                      action="Controleur?action=insererOeuvre"
                      onsubmit="return teste()">
                    <P align="left">
                        <FONT face="Arial" color="#004080"></FONT> <FONT face="Arial"
                                                                         color="#004080"> <BR>&nbsp; &nbsp; &nbsp; Titre de
                        l'oeuvre :
                    </FONT> <INPUT type="text" name="txttitre" value="" id="titre"> <BR>

                        <FONT face="Arial" color="#004080"> <BR>Etat de
                            l'oeuvre :
                        </FONT> <INPUT type="text" name="txtetat" id="etat"> <BR>

                        <FONT face="Arial" color="#004080"> <BR>&nbsp; &nbsp;
                            &nbsp; Prix de l'oeuvre :
                        </FONT> <INPUT type="text" name="txtprix" id="prix"> <FONT
                            face="Arial" color="#004080"> <BR></FONT><BR>

                        <!-- Boutons Ajouter -->
                        <BR>
                        <INPUT type="submit" name="bt" value="Ajouter"> <FONT
                            face="Arial" color="#004080"></FONT> &nbsp;
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <BR>  <BR> <BR>
                    </P>
                </FORM>
                <P>  <BR>  <BR> <BR> </P>
            </div>
            <%@ include file="include/footer.jsp"%>
        </div>
    </div>
</body>
</html>
