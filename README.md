# Spider Crawler

A collector crawler for the smartcampus project.

- O algoritmo ele so pega e recomenda
- As recomendacoes comecam frias
- O tutorial fala brevemente do proposito da aplicacao e convida o usuario a selecionar 3/5 palavras-chave de interesse
- Apos o usuario completar o tutorial, pontos de xp sao ganhados (gameficacao)
- E por assim dizer, teriamos recomendacoes personalizadas aos gostos do usuario
- E o sistema de recomendacao se ajusta conforme as palavras-chave para melhor recomendar

- Caso o usuario nao complete o tutorial, Items serao dispostos (randomicamente/ou em logica), para que o usuario possa explorar
- A logica seria: MAIS VISTOS, MAIS VISUZALIZADOS, MAIS GOSTADOS, NOVIDADES, DA REGIAO
- Assim a recomendacao atuara inicialmente apos o usuario comecar a interagir com os items
- Nessa mesma linha, as recomendacoes sao divididas em diretas e indiretas
- O sistema coleta e registra interacoes, e recomendacoes passadas
- Com interacoes recentes + recomendacoes passadas, o usuario podera revisitar algum item, ou podera prosseguir no "futuro", remodelando como sera sua recomendacao, se interagir sequencialmente com novos items

- Nesse "caminhar do futuro" do usuario, podemos utilizar de redes neurais para encontrar padroes

// salva os projetos
// salva os professores e os participantes
// mas e se professores e participantes quiserem eventualmente se tornarrem usuarios? como proceder?
// validador de certificados, email, telefone
// videoconferencia ou entrevista online
// parcerias com instituicoes

```py

# PRINCIPAL
def SCRAPING_collect_projects
  # store people in list


# UTILS
def SCRAPING_collect_project_members_to_list() -> List[str]:
  return List[str];

def SCRAPING_find_projects_by_professor_name()
def SCRAPING_find_projects_by_student_name()

def SELENIUM_from_list_find_professors_with_selenium()
def SELENIUM_from_lattes_hyperlink_find_professors()

```