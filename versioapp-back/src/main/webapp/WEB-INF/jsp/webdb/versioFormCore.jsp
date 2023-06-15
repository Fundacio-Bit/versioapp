<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="VersioFields" className="org.fundaciobit.versioapp.model.fields.VersioFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,VersioFields.ENTORNAPLICACIOID)}">
        <tr id="versio_entornAplicacioID_rowid">
          <td id="versio_entornAplicacioID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[VersioFields.ENTORNAPLICACIOID])?'versio.entornAplicacioID':__theForm.labels[VersioFields.ENTORNAPLICACIOID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[VersioFields.ENTORNAPLICACIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[VersioFields.ENTORNAPLICACIOID]}" ></i>
              </c:if>
            </td>
          <td id="versio_entornAplicacioID_columnvalueid">
          <form:errors path="versio.entornAplicacioID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,VersioFields.ENTORNAPLICACIOID)}" >
          <form:hidden path="versio.entornAplicacioID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.versio.entornAplicacioID,__theForm.listOfEntornAplicacioForEntornAplicacioID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,VersioFields.ENTORNAPLICACIOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="versio_entornAplicacioID"  onchange="if(typeof onChangeEntornAplicacioID == 'function') {  onChangeEntornAplicacioID(this); };"  cssClass="form-control col-md-9-optional" path="versio.entornAplicacioID">
            <c:forEach items="${__theForm.listOfEntornAplicacioForEntornAplicacioID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,VersioFields.VERSIO)}">
        <tr id="versio_versio_rowid">
          <td id="versio_versio_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[VersioFields.VERSIO])?'versio.versio':__theForm.labels[VersioFields.VERSIO]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[VersioFields.VERSIO]}">
              <i class="fas fa-info-circle" title="${__theForm.help[VersioFields.VERSIO]}" ></i>
              </c:if>
            </td>
          <td id="versio_versio_columnvalueid">
            <form:errors path="versio.versio" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,VersioFields.VERSIO)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,VersioFields.VERSIO)? ' uneditable-input' : ''}"  style="" maxlength="100" path="versio.versio"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,VersioFields.BUILD)}">
        <tr id="versio_build_rowid">
          <td id="versio_build_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[VersioFields.BUILD])?'versio.build':__theForm.labels[VersioFields.BUILD]}" />
             </label>
              <c:if test="${not empty __theForm.help[VersioFields.BUILD]}">
              <i class="fas fa-info-circle" title="${__theForm.help[VersioFields.BUILD]}" ></i>
              </c:if>
            </td>
          <td id="versio_build_columnvalueid">
            <form:errors path="versio.build" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,VersioFields.BUILD)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,VersioFields.BUILD)? ' uneditable-input' : ''}"  style="" maxlength="100" path="versio.build"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,VersioFields.DATA)}">
        <tr id="versio_data_rowid">
          <td id="versio_data_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[VersioFields.DATA])?'versio.data':__theForm.labels[VersioFields.DATA]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[VersioFields.DATA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[VersioFields.DATA]}" ></i>
              </c:if>
            </td>
          <td id="versio_data_columnvalueid">
    <form:errors path="versio.data" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="versio_data" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,VersioFields.DATA)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#versio_data" path="versio.data" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,VersioFields.DATA)}" >
                    <div class="input-group-append"  data-target="#versio_data"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#versio_data').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,VersioFields.ALTRESDADES)}">
        <tr id="versio_altresDades_rowid">
          <td id="versio_altresDades_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[VersioFields.ALTRESDADES])?'versio.altresDades':__theForm.labels[VersioFields.ALTRESDADES]}" />
             </label>
              <c:if test="${not empty __theForm.help[VersioFields.ALTRESDADES]}">
              <i class="fas fa-info-circle" title="${__theForm.help[VersioFields.ALTRESDADES]}" ></i>
              </c:if>
            </td>
          <td id="versio_altresDades_columnvalueid">
              <form:errors path="versio.altresDades" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,VersioFields.ALTRESDADES)? 'true' : 'false'}" path="versio.altresDades"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_altresDades" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_altresDades" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('versio.altresDades'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('versio.altresDades'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('versio.altresDades'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_altresDades').on('click', function(){
					var valor = ($('#dropdownMenuContainer_altresDades').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_altresDades').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
