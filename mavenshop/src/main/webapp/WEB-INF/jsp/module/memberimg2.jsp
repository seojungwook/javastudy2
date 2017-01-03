<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<script>
opener.document.joinform.pictureUrl.value="${picture}";
img = opener.document.getElementById("picture2");
img.src="${pageContext.request.contextPath}/picture/${picture}";
self.close();
</script>