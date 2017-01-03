<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ include file="/WEB-INF/jsp/jspHeader.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
   function disp_div(id){
      document.getElementById('minfo').style.display = 'none';
      document.getElementById('oinfo').style.display = 'none';
      document.getElementById(id).style.display='block';
   }
      function list_disp(id) {
            var disp = document.getElementById(id);
            if(disp.style.display == 'block') {
               disp.style.display = 'none';
            } else {
               disp.style.display = 'block';
            }
         }
</script>

<h2 align="center">My Page</h2>
<table align = "center">
   <tr>
      <td>
         <a href = "javascript:disp_div('minfo')" style="text-decoration: none">ȸ����������</a>
      </td>
      <td>
         <a href = "javascript:disp_div('oinfo')" style="text-decoration: none">�ֹ���������</a>
      </td>
   </tr>
   <tr><td><br><br></td></tr>
</table>

<div id = "minfo" style="display:block;">
   <c:import url="/WEB-INF/jsp/member/info.jsp"></c:import>
</div>
<div id = "oinfo" style="display:none">
   <c:set var="sum" value="0"/>
      <h2 align="center">�ֹ�����</h2>
   <hr width="100%" color="#CCCCCC" style="height: 1px"><br>
   <table border="0" cellspacing="0" cellpadding="0" align = "center" width="50%">
      <tr align = "center">
         <td width="100">�ֹ���ȣ</td>
         <td width="200">�ֹ�����</td>
         <td width="100">�� �հ�</td>
      </tr>
      <tr><td colspan="4"><hr></td></tr>
      <c:forEach items="${salelist}" var="sale" varStatus="stat">
         <tr>
            <td align = "center"><a href="javascript:list_disp('saleLine${stat.index}')" style="text-decoration: none">${sale.saleId}</a></td>
            <td align = "center">
            <f:formatDate value="${sale.updateTime}" pattern="yyyy-MM-dd hh:mm:ss"/>
            </td>
         </tr>
         <tr>
            <td colspan="3">
               <div id="saleLine${stat.index}" style="display: none;">
                  <table border="0" width="100%" > 
                     <tr>
                        <td align = "center" colspan="4"> ��ǰ ��� <br></td>
                     </tr>
                     <tr align = "center">
                        <td width="100">��ǰ��</td>
                        <td width="100">����</td>
                        <td width="100">����</td>
                        <td width="100">�ݾ�</td>
                     </tr>
                  
                  <c:forEach items="${sale.saleLine}" var="saleLine" >
                     <tr align = "center">
                        <td width="100">${saleLine.item.itemName}</td>
                        <td width="100">${saleLine.item.price}</td>
                        <td width="100">${saleLine.quantity}</td>
                        <td width="100">${saleLine.quantity * saleLine.item.price}</td>
                        <c:set var = "sum" value="${sum + (saleLine.quantity * saleLine.item.price)}"/> 
                     </tr>
                     </c:forEach>
                  </table>
               </div>
            </td>
         </tr>
         <tr>
            <td></td>
            <td></td>
            <td  align = "center">
               ${sum}��
            </td>
         </tr>   
            <c:set var = "sum" value = "0"/>
         <tr>
            <td colspan="4"><hr></td>
         </tr>
      </c:forEach>
   </table>
   <br><br>
</div>