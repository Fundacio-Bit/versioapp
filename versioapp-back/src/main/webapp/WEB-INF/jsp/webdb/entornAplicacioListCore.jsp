  <c:if test="${empty entornAplicacioItems}">
     <%@include file="entornAplicacioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty entornAplicacioItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="entornAplicacioListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="entornAplicacioListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="entornAplicacioListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="entornAplicacio" items="${entornAplicacioItems}">

        <tr id="entornAplicacio_rowid_${entornAplicacio.entornAplicacioID}">
          <%@include file="entornAplicacioListCoreMultipleSelect.jsp" %>

          <%@include file="entornAplicacioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="entornAplicacioListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
