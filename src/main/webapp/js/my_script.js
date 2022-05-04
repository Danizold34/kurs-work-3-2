var countOfFields = 2;
var maxFieldLimit = 25;

function addField() {
    if (countOfFields >= maxFieldLimit) {
        return false;
    }
    countOfFields++;
    var tr = document.createElement("tr");
    tr.innerHTML = "                     <td>\n" +
        "                        <input name=\"station\" required type=\"text\" pattern=\"\\w{3,40}\"\n" +
        "                               >\n" +
        "                    </td>\n" +
        "                    <td>\n" +
        "                        <input name=\"time\" required type=\"time\">\n" +
        "                    </td>\n" +
        "                    <td>\n" +
        "                        <input type=\"text\"\n" +
        "                               required\n" +
        "                               name=\"price\"\n" +
        "                               min=\"1\"\n" +
        "                               pattern=\"\\d{1,5}\"\n" +
        "                              >\n" +
        "                    </td>";
    document.getElementById("route").appendChild(tr);
    return false;
}