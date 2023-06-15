<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${entornFilterForm.contexte}"/>
  <c:set var="formName" value="entorn" />
  <c:set var="__theFilterForm" value="${entornFilterForm}" />
  <c:if test="${empty entornFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="entorn.entorn"/>
  </c:if>
  <c:if test="${not empty entornFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${entornFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty entornFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="entorn.entorn"/>
  </c:if>
  <c:if test="${not empty entornFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${entornFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.entorn.submit();  
  }
</script>
