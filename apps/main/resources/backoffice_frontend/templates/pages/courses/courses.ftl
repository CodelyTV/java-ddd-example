<#include "../../master.ftl">

<#macro page_title>Cursos</#macro>

<#macro main>
    <div class="max-w-sm rounded overflow-hidden shadow-lg float-left">
        <img class="w-full" src="https://codely.tv/pro/img/bg/cursos-codelytv-pro.png" alt="Sunset in the mountains">
        <div class="px-6 py-4">
            <div class="font-bold text-xl mb-2">Cursos</div>
            <p class="text-gray-700 text-base">
                Actualmente CodelyTV Pro cuenta con <b>${courses_counter}</b> cursos.
            </p>
        </div>
    </div>

    <#include "partials/new_course_form.ftl">
    <div class="clearfix mb-10"></div>
    <hr class="mb-10">
    <#include "partials/list_courses.ftl">
</#macro>
