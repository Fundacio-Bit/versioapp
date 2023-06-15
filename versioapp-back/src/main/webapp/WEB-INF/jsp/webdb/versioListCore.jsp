  <c:if test="${empty versioItems}">
     <%@include file="versioListEmpty.jsp" %>

  </c:if>
  
  <c:if test="${not empty versioItems}">

  <div class="row" style="margin-left: 0px;">
  <table class="table table-sm table-bordered table-striped table-genapp" style="width:auto;"> 
    <thead>
      <tr>

          <%@include file="versioListCoreHeaderMultipleSelect.jsp" %>

          <%@include file="versioListCoreHeader.jsp" %>

          <%-- ADD HERE NEW COLUMNS HEADER  --%>

          <%@include file="versioListButtonsHeader.jsp" %>

      </tr>
    </thead>
    <tbody>

      <c:forEach var="versio" items="${versioItems}">

        <tr id="versio_rowid_${versio.versioID}">
          <%@include file="versioListCoreMultipleSelect.jsp" %>

          <%@include file="versioListCoreContent.jsp" %>

          <%--  ADD HERE NEW COLUMNS CONTENT --%>


          <%@include file="versioListButtons.jsp" %>


        </tr>

      </c:forEach>

    </tbody>
  </table>
  </div>
  </c:if>
  
