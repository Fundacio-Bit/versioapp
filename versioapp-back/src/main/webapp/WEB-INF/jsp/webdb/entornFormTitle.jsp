<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(entornForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(entornForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty entornForm.titleCode}">
    <fmt:message key="${entornForm.titleCode}" >
      <fmt:param value="${entornForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty entornForm.entityNameCode}">
      <fmt:message var="entityname" key="entorn.entorn"/>
    </c:if>
    <c:if test="${not empty entornForm.entityNameCode}">
      <fmt:message var="entityname" key="${entornForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${entornForm.nou?'genapp.createtitle':(entornForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty entornForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(entornForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(entornForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${entornForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>