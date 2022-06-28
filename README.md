# API busca de raças de gatos


## Testes Unitários
Os testes unitários foi desenvolvido em `JUnit`, e será executado sempre no build.
O projeto contém o plugin do `Jacoco`, com isso é possível visualizar o report de cobertura de testes através de um arquivo HTML.

O Arquivo HTML é gerado sempre na conclusão do build. Como o projeto usa `Maven`, o caminho do report é `target/site/jacoco/index.html`:
<image src="contrib/images/report3.PNG" alt="Localtion">

Ao abrir o arquivo `index.html` no navegador de sua preferência, essa é a visão geral de cobertura do projeto, que no exemplo abaixo 
está em `85%` de código:
<image src="contrib/images/report1.PNG" alt="General Report">

Navegando nas classes, é possivel visualizar as linhas que foram cobertas totalmente, parcialmente e não cobertas:
<image src="contrib/images/report2.PNG" alt="Class Report">