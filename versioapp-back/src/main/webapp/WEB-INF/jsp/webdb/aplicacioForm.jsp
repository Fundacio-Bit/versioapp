
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="aplicacioForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="aplicacioFormTitle.jsp" %>
 
  <c:set var="contexte" value="${aplicacioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="aplicacioFormCorePre.jsp" %>

  <%@include file="aplicacioFormCore.jsp" %>

  <%@include file="aplicacioFormCorePost.jsp" %>

  <%@include file="aplicacioFormButtons.jsp" %>

  <c:if test="${not empty aplicacioForm.sections}">
     <c:set var="__basename" value="aplicacio" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${aplicacioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/aplicacioFormModificable.jsp" %>
  </c:if>

</form:form>


