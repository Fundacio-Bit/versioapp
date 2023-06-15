<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${aplicacioFilterForm.contexte}"/>
  <c:set var="formName" value="aplicacio" />
  <c:set var="__theFilterForm" value="${aplicacioFilterForm}" />
  <c:if test="${empty aplicacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="aplicacio.aplicacio"/>
  </c:if>
  <c:if test="${not empty aplicacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${aplicacioFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty aplicacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="aplicacio.aplicacio"/>
  </c:if>
  <c:if test="${not empty aplicacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${aplicacioFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.aplicacio.submit();  
  }
</script>
