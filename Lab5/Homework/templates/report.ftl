<html>
<head>
  <title>${name} </title>
</head>
<body>
  <h1>${name}</h1>
  <ul>
    <#list entries as entry>
      <li>${entry.id} : ${entry.name}
	<p> additional tags:
		<#list entry.tags?keys as tag>
		 <li> ${tag} : ${entry[tag]} </li>
		</#list>
	</p>
	</li>
    </#list>
  </ul>

</body>
</html>