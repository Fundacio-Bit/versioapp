  <c:if test="${empty entornItems}">
     <%@include file="entornListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty entornItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="entornListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="entornListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="entornListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="entorn" items="${entornItems}">

        <tr id="entorn_rowid_${entorn.entornID}">
          <%@include file="entornListCoreMultipleSelect.jsp" %>

          <%@include file="entornListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="entornListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
