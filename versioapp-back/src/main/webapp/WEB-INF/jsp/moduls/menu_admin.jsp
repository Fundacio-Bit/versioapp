<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<c:set var="url" value="${urlActual}" />
<div>
  <h5>Menú ROLE_ADMIN</h5>
  <ul class="tree" style="margin: 3px; padding: 0px;">


    
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/admin/aplicacio/list"/>">
        <span style="${(fn:contains(url, '/admin/aplicacio/list'))? "font-weight: bold;" : ""}">Llistat d'Aplicacions XYY ZZZ</span>
      </a>
    </li>
    
    
    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    
    
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/admin/entorn/list"/>">
        <span style="${(fn:contains(url, '/admin/entorn/list'))? "font-weight: bold;" : ""}">Llistat d'Entorns XYY ZZZ</span>
      </a>
    </li>
    


   <%-- Example with security: virtual roles  --%>
   <%--
   <sec:authorize access="hasAnyRole('ROLE_SOLI', 'ROLE_DEST', 'ROLE_COLA', 'ROLE_DELE')">
      <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
      <li style="list-style-type: disc; list-style-position: inside;">
       <a href="<c:url value="/admin/rebreAvis/list/1"/>" >
       <span style="${(fn:contains(url, 'optionxxxxx/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
       Option XXXXX</span></a></li>
   </sec:authorize>
    

    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/admin/option2"/>">
        <span style="${(fn:contains(url, 'option2'))? "font-weight: bold;" : ""}">Menú ADMIN Option 2</span>
      </a>
    </li>
   --%>
  </ul>
</div>

