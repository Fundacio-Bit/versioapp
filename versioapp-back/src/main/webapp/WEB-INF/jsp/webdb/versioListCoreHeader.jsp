<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="VersioFields" className="org.fundaciobit.versioapp.model.fields.VersioFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${ver:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,VersioFields.VERSIOID)}">
        <th>${ver:getSortIcons(__theFilterForm,VersioFields.VERSIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,VersioFields.ENTORNAPLICACIOID)}">
        <th>${ver:getSortIcons(__theFilterForm,VersioFields.ENTORNAPLICACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,VersioFields.VERSIO)}">
        <th>${ver:getSortIcons(__theFilterForm,VersioFields.VERSIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,VersioFields.BUILD)}">
        <th>${ver:getSortIcons(__theFilterForm,VersioFields.BUILD)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,VersioFields.DATA)}">
        <th>${ver:getSortIcons(__theFilterForm,VersioFields.DATA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,VersioFields.ALTRESDADES)}">
        <th>${ver:getSortIcons(__theFilterForm,VersioFields.ALTRESDADES)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${ver:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

