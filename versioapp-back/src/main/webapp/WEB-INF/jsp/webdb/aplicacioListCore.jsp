  <c:if test="${empty aplicacioItems}">
     <%@include file="aplicacioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty aplicacioItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="aplicacioListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="aplicacioListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="aplicacioListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="aplicacio" items="${aplicacioItems}">

        <tr id="aplicacio_rowid_${aplicacio.aplicacioID}">
          <%@include file="aplicacioListCoreMultipleSelect.jsp" %>

          <%@include file="aplicacioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="aplicacioListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
