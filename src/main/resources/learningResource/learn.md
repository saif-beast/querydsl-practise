#### QueryDSL Annotation processor with gradle
https://blog.jdriven.com/2018/10/using-querydsl-annotation-processor-with-gradle-and-intellij-idea/

#### Intro to querydsl
https://www.baeldung.com/intro-to-querydsl

#### QueryDSL Reference
http://www.querydsl.com/static/querydsl/4.1.4/reference/html/



#### Learning

You need to create a `JPAQueryFactory` to query using Q-Type meta-model classes

##### Creating an object of `JPAQueryFactory` using `EntityManager`
`
JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager)
`