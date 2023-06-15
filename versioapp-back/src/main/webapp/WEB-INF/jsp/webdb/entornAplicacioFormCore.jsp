<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EntornAplicacioFields" className="org.fundaciobit.versioapp.model.fields.EntornAplicacioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntornAplicacioFields.APLICACIOID)}">
        <tr id="entornAplicacio_aplicacioID_rowid">
          <td id="entornAplicacio_aplicacioID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntornAplicacioFields.APLICACIOID])?'entornAplicacio.aplicacioID':__theForm.labels[EntornAplicacioFields.APLICACIOID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EntornAplicacioFields.APLICACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntornAplicacioFields.APLICACIOID]}" ></i>
              </c:if>
            </td>
          <td id="entornAplicacio_aplicacioID_columnvalueid">
          <form:errors path="entornAplicacio.aplicacioID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntornAplicacioFields.APLICACIOID)}" >
          <form:hidden path="entornAplicacio.aplicacioID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.entornAplicacio.aplicacioID,__theForm.listOfAplicacioForAplicacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntornAplicacioFields.APLICACIOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="entornAplicacio_aplicacioID"  onchange="if(typeof onChangeAplicacioID == 'function') {  onChangeAplicacioID(this); };"  cssClass="form-control col-md-9-optional" path="entornAplicacio.aplicacioID">
            <c:forEach items="${__theForm.listOfAplicacioForAplicacioID}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,EntornAplicacioFields.ENTORNID)}">
        <tr id="entornAplicacio_entornid_rowid">
          <td id="entornAplicacio_entornid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[EntornAplicacioFields.ENTORNID])?'entornAplicacio.entornid':__theForm.labels[EntornAplicacioFields.ENTORNID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[EntornAplicacioFields.ENTORNID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[EntornAplicacioFields.ENTORNID]}" ></i>
              </c:if>
            </td>
          <td id="entornAplicacio_entornid_columnvalueid">
          <form:errors path="entornAplicacio.entornid" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,EntornAplicacioFields.ENTORNID)}" >
          <form:hidden path="entornAplicacio.entornid"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.entornAplicacio.entornid,__theForm.listOfEntornForEntornid)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,EntornAplicacioFields.ENTORNID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="entornAplicacio_entornid"  onchange="if(typeof onChangeEntornid == 'function') {  onChangeEntornid(this); };"  cssClass="form-control col-md-9-optional" path="entornAplicacio.entornid">
            <c:forEach items="${__theForm.listOfEntornForEntornid}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
