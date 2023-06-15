      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${entornAplicacio.entornAplicacioID}"/>
       &nbsp;
      </td>
      </c:if>

