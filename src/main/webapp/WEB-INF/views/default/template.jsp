<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Default tiles template</title>
</head>
<body>
    <div class="page">
        <tiles:insertAttribute name="header" />
        <tiles:insertAttribute name="menu" />
        <div class="container">
			<div class="bankofspring_body_container">
            	<tiles:insertAttribute name="body" />
            </div>
        </div>
        <tiles:insertAttribute name="footer" />
    </div>
</body>
</html>