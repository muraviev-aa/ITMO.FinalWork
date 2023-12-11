![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
## ITMO.FinalWork.
<a name="readme-top"></a>
### Настольное приложение для расчета стальных балок на бимомент.

#### Актуальность разработки: расчет балок на бимомент стал актуальным с выходом в свет СП 16.13330.2011
#### "Стальные конструкции".

#### Цель разработки: реализация расчета на прочность изгибаемых элементов сплошного сечения по формуле 43 
#### СП 16.13330.2017 с учетом бимомента (буквенное обозначение - B, см. рис.)

<p align="center">
<img src="images/f43.png" alt="drawing" width="400"/>
</p>

***Кнопки диалоговых окон приложения снабжены всплывающими подсказками:***

<p align="center">
<img src="images/selection.png" alt="drawing" width="400"/>
</p>

***Предусмотрена работа со следующими типами сечений:***

- прокатный швеллер с параллельными гранями полок;
- прокатный швеллер с уклоном внутренних граней полок;
- прокатный двутавр;
- двутавр сварной с двумя осями симметрии;
- двутавр сварной с одной осью симметрии.

<p align="center">
<img src="images/specifications.png" alt="drawing" width="750"/>
</p>

***Вычисленные секториальные характеристики сечения обозначены в диалоговом окне синим цветом.***

<p align="center">
<img src="images/table.png" alt="drawing" width="400"/>
</p>

***Предусмотрена возможность выбора необходимого сечения из БД.***

<p align="center">
<img src="images/steel.png" alt="drawing" width="750"/>
</p>

***Предусмотрен выбор категории стали и расчетной схемы. Реализована возможность указания***
***необходимых линейных размеров балки.***

<p align="center">
<img src="images/result.png" alt="drawing" width="750"/>
</p>

***В ходе расчета определяется коэффициент использования сечения и процентный вклад бимомента в общее***
***напряженное состояние сечения. Если балка "не проходит" по результатам расчета,***
***появляется спец. знак красного цвета с восклицательным знаком.***

<p align="right">(<a href="#readme-top">back to top</a>)</p>

