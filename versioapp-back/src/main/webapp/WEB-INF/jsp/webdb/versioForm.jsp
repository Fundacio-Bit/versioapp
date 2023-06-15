
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="versioForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="versioFormTitle.jsp" %>
 
  <c:set var="contexte" value="${versioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="versioFormCorePre.jsp" %>

  <%@include file="versioFormCore.jsp" %>

  <%@include file="versioFormCorePost.jsp" %>

  <%@include file="versioFormButtons.jsp" %>

  <c:if test="${not empty versioForm.sections}">
     <c:set var="__basename" value="versio" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${versioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/versioFormModificable.jsp" %>
  </c:if>

</form:form>


