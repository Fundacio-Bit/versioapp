<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AplicacioFields" className="org.fundaciobit.versioapp.model.fields.AplicacioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,AplicacioFields.NOM)}">
        <tr id="aplicacio_nom_rowid">
          <td id="aplicacio_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AplicacioFields.NOM])?'aplicacio.nom':__theForm.labels[AplicacioFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[AplicacioFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AplicacioFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="aplicacio_nom_columnvalueid">
            <form:errors path="aplicacio.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AplicacioFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,AplicacioFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="aplicacio.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AplicacioFields.CONTEXTPATH)}">
        <tr id="aplicacio_contextPath_rowid">
          <td id="aplicacio_contextPath_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AplicacioFields.CONTEXTPATH])?'aplicacio.contextPath':__theForm.labels[AplicacioFields.CONTEXTPATH]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[AplicacioFields.CONTEXTPATH]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AplicacioFields.CONTEXTPATH]}" ></i>
              </c:if>
            </td>
          <td id="aplicacio_contextPath_columnvalueid">
            <form:errors path="aplicacio.contextPath" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AplicacioFields.CONTEXTPATH)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,AplicacioFields.CONTEXTPATH)? ' uneditable-input' : ''}"  style="" maxlength="255" path="aplicacio.contextPath"   />

           </td>
        </tr>
        </c:if>
        
