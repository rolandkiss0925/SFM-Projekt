mvn -U clean archetype:create-from-project -Dinteractive=false  -DkeepParent=true  -DpropertyFile=archetype.properties  -DpackageName=hu.unideb.inf -Darchetype.filteredExtensions=java,xml,md
cd target\generated-sources\archetype
mvn -B -U clean install -Dmaven.repo.local=C:\Users\Gergely\arch_repo