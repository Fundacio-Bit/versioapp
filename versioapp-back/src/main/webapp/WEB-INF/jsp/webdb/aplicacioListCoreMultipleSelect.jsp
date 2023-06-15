      <%--  CHECK DE SELECCIO MULTIPLE  --%>
      <c:if test="${__theFilterForm.visibleMultipleSelection}">
      <td>
       <form:checkbox path="selectedItems" value="${aplicacio.aplicacioID}"/>
       &nbsp;
      </td>
      </c:if>

