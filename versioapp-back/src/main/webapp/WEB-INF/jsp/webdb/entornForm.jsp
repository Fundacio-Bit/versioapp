
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="entornForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="entornFormTitle.jsp" %>
 
  <c:set var="contexte" value="${entornForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="entornFormCorePre.jsp" %>

  <%@include file="entornFormCore.jsp" %>

  <%@include file="entornFormCorePost.jsp" %>

  <%@include file="entornFormButtons.jsp" %>

  <c:if test="${not empty entornForm.sections}">
     <c:set var="__basename" value="entorn" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${entornForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/entornFormModificable.jsp" %>
  </c:if>

</form:form>


