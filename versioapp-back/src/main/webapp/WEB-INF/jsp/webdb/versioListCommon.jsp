<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${versioFilterForm.contexte}"/>
  <c:set var="formName" value="versio" />
  <c:set var="__theFilterForm" value="${versioFilterForm}" />
  <c:if test="${empty versioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="versio.versio"/>
  </c:if>
  <c:if test="${not empty versioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${versioFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty versioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="versio.versio"/>
  </c:if>
  <c:if test="${not empty versioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${versioFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.versio.submit();  
  }
</script>
