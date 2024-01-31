package com.example.models;


import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Builder
@ToString
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logo; // additional
    private String title;
    private String numberUnique; // TODOS: UniqueIdentifier
    private Classification classification;
    // private String description;
    private String summary; // old description (migh not exist)
    private String objectives; // old description (migh not exist)
    private String defense; // old description (migh not exist) "Not informed"
    private String results; // old description (migh not exist)

    private LocalDate dateStart;
    private LocalDate dateFinal;
    private LocalDate publicationDate;
    private LocalDate completionDate;

    private Status status;

    @OneToMany
    private Set<Keyword> keywords;

    public enum Classification {
        DEFAULT,
        ENSINO,
        EXTENSAO,
        PESQUISA,
        DESENVOLVIMENTO_INSTITUCIONAL
    }

    public enum Status {
        DEFAULT,
        SUSPENSO,
        CONCLUIDO_PUBLICADO,
        CANCELADO,
        EM_ANDAMENTO
    }

}

// O presente projeto servirá como um Programa guarda-chuva para as ações de extensão realizadas no Museu Educativo Gama dEça, vinculado a Pró-Reitoria de Extensão (PRE), da Universidade Federal de Santa Maria (UFSM). Tais ações são consideradas importantes para que o museu cumpra com as suas funções básicas, em especial, no que diz respeito a realização de atividades que visam garantir a preservação do seu acervo, para as gerações atuais e futuras, bem como a comunicação deste acervo, através de exposições, divulgação virtual, publicações e atividades educativas.

// NTOE: This one has all atributes
// NOTE: https://portal.ufsm.br/projetos/publico/projetos/view.html?idProjeto=68644 
// NOTE: This other one, dont
// NOTE: https://portal.ufsm.br/projetos/publico/projetos/view.html?idProjeto=34648

// Esta descrição tem como objetivo apresentar o projeto RELAÇÕES ENTRE BIOMIMÉTICA, PENSAMENTO SISTÊMICO E CONHECIMENTOS INDÍGENAS: CONCEPÇÃO DE UM GUIA PARA TOMADA DE DECISÕES ARQUITETÔNICAS REGENERATIVAS (projeto 056190).

// Objetivo:
// Objetivo geral: Elaborar um guia arquitetônico regenerativo apropriado para o contexto brasileiro, a partir da intersecção de saberes biomiméticos, sistêmicos e indígenas, representado de forma visual.
// Objetivos específicos: 
// a) verificar o estado da arte da temática de pesquisa;
// b) investigar a biomimética, o pensamento sistêmico e o design regenerativo em um contexto arquitetônico;
// c) examinar quais as possíveis simbioses entre ambiente construído e ambiente natural;
// d) reconhecer saberes arquitetônicos indígenas e investigar relações regenerativas entre habitação-humano-natureza..

// Justificativa:
// A pegada ecológica da humanidade ultrapassou a capacidade bioprodutiva da Terra pela primeira vez no início dos anos 1970, e a cada ano se consome mais recursos e se produz mais resíduos do que a capacidade de regeneração e absorção da bioatividade natural do planeta. E o dia de sobrecarga da Terra está ocorrendo cada vez mais cedo: dia 4 de novembro em 1980, dia 11 de outubro em 1990, 23 de Setembro no ano 2000, 7 de agosto em 2010 e dia 29 de julho em 2019. Isso significa que hoje são necessários 1.6 planetas para produzir os recursos na mesma velocidade que a humanidade os consome (Global Footprint Network, 2020).
// Embora os seres humanos possam acreditar na aparente imensidão e infinitude do mundo natural, o desequilíbrio que se provoca na natureza é alarmante e sem precedentes. Segundo pesquisa de Elhacham et. al (2020), pela primeira vez na história a massa de objetos produzidos pelo homem ultrapassou a massa total de matéria viva na Terra, sendo que a massa global de plástico produzido é maior do que a massa total de todos os animais terrestres e marinhos combinados. As duas categorias dominantes na análise são os edifícios e infraestrutura (composta de concreto, agregados, tijolos e asfalto) e árvores e arbustos (a maioria da massa vegetal e, portanto, da biomassa total). Os dados são assustadores, pois no início do século XX a massa antropogênica constituia apenas 3% da biomassa global. Apenas 120 anos depois, em 2020, a massa antropogênica está excedendo a biomassa total no mundo, devido ao maior consumo e desenvolvimento urbano. O estudo prevê ainda que se as tendências de crescimento atuais continuarem, a massa total de objetos produzidos pelo homem, incluindo resíduos, deverá exceder 3 teratoneladas (Tt, unidades de 10¹² toneladas) até 2040 - quase o triplo da biomassa seca da Terra.
// Nesse contexto de degeneração, a neutralidade da sustentabilidade não é mais o suficiente. Conforme praticada atualmente, a sustentabilidade é principalmente um exercício de eficiência, ou em outras palavras, a redução dos danos causados pelo uso excessivo de recursos. No entanto, é preciso fazer mais do que apenas sustentar o modo de vida atual. É preciso regenerar a vitalidade do sistema de suporte à vida e nutrir o padrão de interdependências socioecológicas que ancoram a saúde humana e planetária (WAHL, 2016).
// A sustentabilidade na arquitetura, tal como entendida pela sociedade moderna, é uma medida inadequada para a manutenção da vida na Terra em longo prazo, pois visa não mais do que tentar tornar os edifícios menos ruins. Enquanto a arquitetura sustentável se concentra na eficiência e desempenho para reduzir danos através da seleção de materiais, consumo reduzido de energia e inteligência de projeto, a arquitetura regenerativa envolve uma prática holística que emprega a compreensão completa e abrangente dos sistemas naturais. É uma arquitetura que abraça o meio ambiente e se inspira em milhões de anos de engenharia e evolução da natureza.
// Centenas de edifícios verdes estão sendo construídos mundialmente e estão fazendo sua parte prejudicando menos o planeta. Mas isso não é suficiente. Um planeta repleto de milhões e milhões de edifícios que causam menos danos, ainda não resolve o problema. É preciso realizar mais do que simplesmente causar menos danos, fazer melhor do que apenas desacelerar a rota de colisão (BOECKER et al., 2009). Porém, até agora, ainda não há um ponto de alavancagem claro ou de uma metodologia aceita e estabelecida para mudar a maneira como se projeta e constrói. Como essa transformação pode ocorrer? Onde, no processo de design atual, existe o ponto em que se pode intervir para criar mudanças em grande escala? A resposta, simplesmente, é que ele não existe no processo atual (BOECKER et al., 2009, tradução nossa). Dessa forma, diretrizes que orientem outra maneira de pensar, mais regenerativa e holística, são importantes para auxiliar nesse processo de tomada de decisões e mudança coletiva de paradigmas em uma equipe de projeto..

// Resultados esperados:
// Espera-se que os dados reunidos nas etapas de revisão bibliográfica (quadro comparativo entre critérios de ferramentas emergentes de suporte à prática arquitetônica regenerativa, princípios da vida da biomimética, conceitos de pensamento sistêmico e design regenerativo) e entrevistas com os indígenas ofereçam orientação em relação à compreensão de como se pode sintetizar princípios arquitetônicos regenerativos em uma estrutura que facilite a implementação da mudança necessária nos paradigmas de projeto atuais.
// A intenção na criação das diretrizes é mudar a mentalidade em direção ao pensamento regenerativo e inspirar ações positivas ao longo do ciclo de vida de um projeto. Busca-se facilitar processos de decisão integrativos e que proporcionem soluções tangíveis apoiando a recuperação e manutenção de sistemas naturais, sociais e econômicos saudáveis através de uma representação dinâmica, que auxilie na visualização das interconexões entre os diferentes elementos que compõem as etapas de projeto.
// Além de contar com um diagrama visual, as diretrizes irão ser detalhadas através de informações descritivas e perguntas instigadoras, que oferecem flexibilidade na criação de soluções apropriadas para cada projeto. A ideia é evitar regras e regulamentos rígidos que podem levar a soluções contextualmente inadequadas, portanto soluções específicas não são predeterminadas: a ferramenta auxilia as pessoas por meio de um processo intencional de descoberta, encorajando a criatividade e a tomada de decisão coletiva para distinguir resultados regenerativos de resultados sustentáveis ou menos ruins. 
// A escolha por perguntas ao invés de uma única pontuação ou classificação comparativa é considerada uma abordagem justificável, pois a estrutura não será usada para avaliar o desempenho absoluto de edifícios para que eles possam ser comparados entre si. Muitas das capacidades regenerativas são amplas, complexas e dependem do contexto local em que estão inseridas, não sendo facilmente reduzidas ou descritas por métricas simples  porém, princípios orientativos dentro de cada categoria como consumo de energia, água e materiais serão bem estabelecidos e poderão ser comparados entre si com o uso da ferramenta.
// Embora seja extremamente difícil abraçar totalmente o pensamento sistêmico que compreende o design regenerativo em uma única ferramenta, é necessária a concepção de diretrizes que auxiliem na mudança de paradigmas de sustentabilidade para paradigmas de regeneração. A ênfase está na mudança do processo para obter diferentes resultados de design, e apresentar apenas estratégias prescritas limitaria as possibilidades da mesma forma que as listas de verificação de requisitos de desempenho das atuais ferramentas de avaliação de edifícios verdes fazem. 
// No processo de desenvolvimento, as diretrizes serão moldadas pelos seguintes requisitos: possuírem fundamentos conceituais claros - a estrutura e organização das diretrizes devem oferecer valor educacional e, em vez de simplesmente ser uma lista de questões de desempenho, deverão transmitir uma compreensão entre os relacionamentos entre critérios e processos-chave interligados; serem adaptáveis para diferentes situações; reconhecerem que muitos usuários da estrutura não estarão familiarizados com os conceitos da regeneração, e oferecerem uma orientação clara, porém aberta que proporcione espaços de criação coletiva através do diálogo.
// Pretende-se que as diretrizes possam ser usadas como complemento a outras ferramentas de construção ecológica e sistemas de classificação, oferecendo uma orientação contínua durante o projeto, construção e operações. Isso é alcançado através da participação de pessoas em atividades que unam os diferentes profissionais envolvidos no projeto de forma integrativa, incentivando a conexão interpessoal e fornecendo uma perspectiva coletiva sobre as questões e desafios enfrentados..

// Classificação principal:
// Pesquisa.

// Número do processo:
// 23081.049656/2021-14.

// Resultados esperados:
// Espera-se que os dados reunidos nas etapas de revisão bibliográfica (quadro comparativo entre critérios de ferramentas emergentes de suporte à prática arquitetônica regenerativa, princípios da vida da biomimética, conceitos de pensamento sistêmico e design regenerativo) e entrevistas com os indígenas ofereçam orientação em relação à compreensão de como se pode sintetizar princípios arquitetônicos regenerativos em uma estrutura que facilite a implementação da mudança necessária nos paradigmas de projeto atuais.
// A intenção na criação das diretrizes é mudar a mentalidade em direção ao pensamento regenerativo e inspirar ações positivas ao longo do ciclo de vida de um projeto. Busca-se facilitar processos de decisão integrativos e que proporcionem soluções tangíveis apoiando a recuperação e manutenção de sistemas naturais, sociais e econômicos saudáveis através de uma representação dinâmica, que auxilie na visualização das interconexões entre os diferentes elementos que compõem as etapas de projeto.
// Além de contar com um diagrama visual, as diretrizes irão ser detalhadas através de informações descritivas e perguntas instigadoras, que oferecem flexibilidade na criação de soluções apropriadas para cada projeto. A ideia é evitar regras e regulamentos rígidos que podem levar a soluções contextualmente inadequadas, portanto soluções específicas não são predeterminadas: a ferramenta auxilia as pessoas por meio de um processo intencional de descoberta, encorajando a criatividade e a tomada de decisão coletiva para distinguir resultados regenerativos de resultados sustentáveis ou menos ruins. 
// A escolha por perguntas ao invés de uma única pontuação ou classificação comparativa é considerada uma abordagem justificável, pois a estrutura não será usada para avaliar o desempenho absoluto de edifícios para que eles possam ser comparados entre si. Muitas das capacidades regenerativas são amplas, complexas e dependem do contexto local em que estão inseridas, não sendo facilmente reduzidas ou descritas por métricas simples  porém, princípios orientativos dentro de cada categoria como consumo de energia, água e materiais serão bem estabelecidos e poderão ser comparados entre si com o uso da ferramenta.
// Embora seja extremamente difícil abraçar totalmente o pensamento sistêmico que compreende o design regenerativo em uma única ferramenta, é necessária a concepção de diretrizes que auxiliem na mudança de paradigmas de sustentabilidade para paradigmas de regeneração. A ênfase está na mudança do processo para obter diferentes resultados de design, e apresentar apenas estratégias prescritas limitaria as possibilidades da mesma forma que as listas de verificação de requisitos de desempenho das atuais ferramentas de avaliação de edifícios verdes fazem. 
// No processo de desenvolvimento, as diretrizes serão moldadas pelos seguintes requisitos: possuírem fundamentos conceituais claros - a estrutura e organização das diretrizes devem oferecer valor educacional e, em vez de simplesmente ser uma lista de questões de desempenho, deverão transmitir uma compreensão entre os relacionamentos entre critérios e processos-chave interligados; serem adaptáveis para diferentes situações; reconhecerem que muitos usuários da estrutura não estarão familiarizados com os conceitos da regeneração, e oferecerem uma orientação clara, porém aberta que proporcione espaços de criação coletiva através do diálogo.
// Pretende-se que as diretrizes possam ser usadas como complemento a outras ferramentas de construção ecológica e sistemas de classificação, oferecendo uma orientação contínua durante o projeto, construção e operações. Isso é alcançado através da participação de pessoas em atividades que unam os diferentes profissionais envolvidos no projeto de forma integrativa, incentivando a conexão interpessoal e fornecendo uma perspectiva coletiva sobre as questões e desafios enfrentados..

// Tipo de evento:
// Não se aplica.

// Avaliação:
// Sem pendências de avaliação.

