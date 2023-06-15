<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(aplicacioForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(aplicacioForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty aplicacioForm.titleCode}">
    <fmt:message key="${aplicacioForm.titleCode}" >
      <fmt:param value="${aplicacioForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty aplicacioForm.entityNameCode}">
      <fmt:message var="entityname" key="aplicacio.aplicacio"/>
    </c:if>
    <c:if test="${not empty aplicacioForm.entityNameCode}">
      <fmt:message var="entityname" key="${aplicacioForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${aplicacioForm.nou?'genapp.createtitle':(aplicacioForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty aplicacioForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(aplicacioForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(aplicacioForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${aplicacioForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>