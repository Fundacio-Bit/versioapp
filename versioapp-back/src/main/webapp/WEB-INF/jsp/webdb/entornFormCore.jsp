<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EntornFields" className="org.fundaciobit.versioapp.model.fields.EntornFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntornFields.NOM)}">
        <tr id="entorn_nom_rowid">
          <td id="entorn_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntornFields.NOM])?'entorn.nom':__theForm.labels[EntornFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EntornFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntornFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="entorn_nom_columnvalueid">
            <form:errors path="entorn.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntornFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,EntornFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="100" path="entorn.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntornFields.DOMINI)}">
        <tr id="entorn_domini_rowid">
          <td id="entorn_domini_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntornFields.DOMINI])?'entorn.domini':__theForm.labels[EntornFields.DOMINI]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EntornFields.DOMINI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntornFields.DOMINI]}" ></i>
              </c:if>
            </td>
          <td id="entorn_domini_columnvalueid">
            <form:errors path="entorn.domini" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntornFields.DOMINI)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,EntornFields.DOMINI)? ' uneditable-input' : ''}"  style="" maxlength="255" path="entorn.domini"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntornFields.ORDRE)}">
        <tr id="entorn_ordre_rowid">
          <td id="entorn_ordre_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntornFields.ORDRE])?'entorn.ordre':__theForm.labels[EntornFields.ORDRE]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EntornFields.ORDRE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntornFields.ORDRE]}" ></i>
              </c:if>
            </td>
          <td id="entorn_ordre_columnvalueid">
            <form:errors path="entorn.ordre" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,EntornFields.ORDRE)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,EntornFields.ORDRE)? ' uneditable-input' : ''}"  style=""  path="entorn.ordre"   />

           </td>
        </tr>
        </c:if>
        
