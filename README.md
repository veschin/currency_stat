## Статистика валют за N дней
---
Собирает с [API центробанка](https://www.cbr-xml-daily.ru/daily_json.js) значение курса за N дней по списку валют.

### Документация
---
### Файл core
Константы
    
    ;Количество дней для сбора статистики
    (def days int)
    
    ;Ссылка на API
    (def url string)

    ;Список ключей
    (def currency-keys [:CharCode])
### Файл request
Функция get-currency принимает количество дней, ссылку и список кодов валют в виде ключей. Возвращает список из мап.   
    
    [int string [list of keywords]] -> list of {:day N :CharCode Value}  
    request/get-currency

### Файл db


