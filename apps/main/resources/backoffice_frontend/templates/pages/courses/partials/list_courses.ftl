<h3 class="font-sans text-gray-800 text-center text-3xl mb-10">Cursos existentes</h3>

<table class="text-left w-full border-collapse">
    <thead>
    <tr>
        <th class="py-4 px-6 bg-grey-lightest font-bold uppercase text-sm text-grey-dark border-b border-grey-light">Id</th>
        <th class="py-4 px-6 bg-grey-lightest font-bold uppercase text-sm text-grey-dark border-b border-grey-light">Nombre</th>
        <th class="py-4 px-6 bg-grey-lightest font-bold uppercase text-sm text-grey-dark border-b border-grey-light">Duración</th>
    </tr>
    </thead>
    <tbody id="courses-list">

    </tbody>
</table>

<script>
    const tableBody = document.getElementById("courses-list");

    fetch("/api/courses")
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

    function tableCellFor(text) {
        const cell = document.createElement("td");

        cell.appendChild(document.createTextNode(text));

        return cell;
    }
</script>
