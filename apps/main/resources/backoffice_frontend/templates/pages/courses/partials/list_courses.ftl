<h3 class="font-sans text-gray-800 text-center text-3xl mb-10">Cursos existentes</h3>


<form action="" method="get" id="courses-filters" name="filter-courses">
    <div id="filter-rows">

    </div>
    <div class="clearfix"></div>
    <div class="mt-10 inline-block relative w-2/4">
        <button class="md:w-1/4 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                id="add-field-button"
                onclick="addFilter(event)">
            Añadir filtro
        </button>

        <button class="md:w-1/4 bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
                id="filter-button"
                onclick="filterCourses(event)">
            👉 Filtrar 👈
        </button>
    </div>
</form>
<table class="text-left w-full border-collapse">
    <thead>
    <tr>
        <th class="py-4 px-6 bg-grey-lightest font-bold uppercase text-sm text-grey-dark border-b border-grey-light">
            Id
        </th>
        <th class="py-4 px-6 bg-grey-lightest font-bold uppercase text-sm text-grey-dark border-b border-grey-light">
            Nombre
        </th>
        <th class="py-4 px-6 bg-grey-lightest font-bold uppercase text-sm text-grey-dark border-b border-grey-light">
            Duración
        </th>
    </tr>
    </thead>
    <tbody id="courses-list">

    </tbody>
</table>

<script>


    function addCoursesList(url) {
        console.log(url);

        const tableBody = document.getElementById("courses-list");

        fetch(encodeURI(url))
            .then(function (response) {
                return response.json();
            })
            .then(function (courses) {
                tableBody.innerHTML = "";

                courses.forEach(function (course) {
                    let courseRow = document.createElement("tr");

                    courseRow.appendChild(tableCellFor(course.id));
                    courseRow.appendChild(tableCellFor(course.name));
                    courseRow.appendChild(tableCellFor(course.duration));

                    tableBody.appendChild(courseRow);
                })
            });
    }

    function tableCellFor(text) {
        const cell = document.createElement("td");

        cell.appendChild(document.createTextNode(text));

        return cell;
    }

    function addFilter(e) {
        e.preventDefault();

        const filterRows = document.getElementById('filter-rows');
        const totalRows  = document.getElementById('filter-rows').childElementCount;

        const filterRowTemplate = "<div class=\"filter-row\">\n" +
                                  "    <div class=\"inline-block relative w-64 mr-3\">\n" +
                                  "        <label class=\"block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2\" for=\"field\">\n" +
                                  "            Campo\n" +
                                  "        </label>\n" +
                                  "        <select name=\"filters[" +
                                  totalRows +
                                  "][field]\" id=\"field\"\n" +
                                  "                class=\"block appearance-none w-full bg-white border border-gray-400 hover:border-gray-500 px-4 py-2 pr-8 rounded shadow leading-tight focus:outline-none focus:shadow-outline\">\n" +
                                  "            <option value=\"id\">Identificador</option>\n" +
                                  "            <option value=\"name\">Nombre</option>\n" +
                                  "            <option value=\"duration\">Duración</option>\n" +
                                  "        </select>\n" +
                                  "        <div class=\"pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-700\">\n" +
                                  "            <svg class=\"fill-current h-4 w-4\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 20 20\">\n" +
                                  "                <path d=\"M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z\"/>\n" +
                                  "            </svg>\n" +
                                  "        </div>\n" +
                                  "    </div>\n" +
                                  "    <div class=\"inline-block relative w-64 mr-3\">\n" +
                                  "        <label class=\"block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2\" for=\"operator\">\n" +
                                  "            Operador\n" +
                                  "        </label>\n" +
                                  "        <select id=\"operator\" name=\"filters[" +
                                  totalRows +
                                  "][operator]\"\n" +
                                  "                class=\"block appearance-none w-full bg-white border border-gray-400 hover:border-gray-500 px-4 py-2 pr-8 rounded shadow leading-tight focus:outline-none focus:shadow-outline\">\n" +
                                  "            <option value=\"=\">es exactamente igual</option>\n" +
                                  "            <option value=\"CONTAINS\">contiene</option>\n" +
                                  "            <option value=\">\">es más grande que</option>\n" +
                                  "            <option value=\"<\">es más pequeño que</option>\n" +
                                  "        </select>\n" +
                                  "        <div class=\"pointer-events-none absolute inset-y-0 right-0 flex items-center px-2 text-gray-700\">\n" +
                                  "            <svg class=\"fill-current h-4 w-4\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 20 20\">\n" +
                                  "                <path d=\"M9.293 12.95l.707.707L15.657 8l-1.414-1.414L10 10.828 5.757 6.586 4.343 8z\"/>\n" +
                                  "            </svg>\n" +
                                  "        </div>\n" +
                                  "    </div>\n" +
                                  "    <div class=\"inline-block relative w-64 mr-3\">\n" +
                                  "        <label class=\"block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2\" for=\"value\">\n" +
                                  "            Valor\n" +
                                  "        </label>\n" +
                                  "        <input class=\"appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 rounded py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500\"\n" +
                                  "               id=\"value\" type=\"text\" name=\"filters[" +
                                  totalRows +
                                  "][value]\" placeholder=\"Lo que sea...\">\n" +
                                  "    </div>\n" +
                                  "</div>";

        filterRows.insertAdjacentHTML('beforeend', filterRowTemplate);
    }

    function filterCourses(e) {
        e.preventDefault();

        const form = document.forms["filter-courses"];

        const inputs = Array.from(form.getElementsByTagName("input"))
                            .concat(Array.from(form.getElementsByTagName("select")));

        const urlParts = inputs.map(input => input.name + "=" + input.value);

        const url = "http://localhost:8040/courses?" + urlParts.join("&");

        addCoursesList(url);
    }
</script>

<script>
    addCoursesList("http://localhost:8040/courses");
</script>
