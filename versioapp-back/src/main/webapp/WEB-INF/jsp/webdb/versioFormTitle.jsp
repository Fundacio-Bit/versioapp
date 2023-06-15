<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(versioForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(versioForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty versioForm.titleCode}">
    <fmt:message key="${versioForm.titleCode}" >
      <fmt:param value="${versioForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty versioForm.entityNameCode}">
      <fmt:message var="entityname" key="versio.versio"/>
    </c:if>
    <c:if test="${not empty versioForm.entityNameCode}">
      <fmt:message var="entityname" key="${versioForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${versioForm.nou?'genapp.createtitle':(versioForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty versioForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(versioForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(versioForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${versioForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>