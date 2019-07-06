# selenium tests to search cars by name and price
Simple call:
mvn clean test -Dbrowser=Chrome -Dcar=Kia -DstartPrice="8000 $" -DendPrice="12 000 $"
## parameters
browser - browser name. Posible options: Chrome/Firefox

car - car name. Expample:Ford/Kia/Citroen

startPrice - start Price to search. Posible options:1000 $-100 000 $.

endPrice - start Price to search. Posible options:1000 $-100 000 $.
