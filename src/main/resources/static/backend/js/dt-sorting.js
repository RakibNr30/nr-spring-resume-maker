document.addEventListener('DOMContentLoaded', function () {
    const tableHeaders = document.querySelectorAll('th');

    const urlParams = new URLSearchParams(window.location.search);
    const sortParam = urlParams.get('sort');
    const directionParam = urlParams.get('direction');

    if (!sortParam && !directionParam) {
        const defaultHeader = document.getElementById('id');
        if (defaultHeader) {
            defaultHeader.classList.add('sorting_asc');
        }
    } else if (sortParam && directionParam) {
        const sortedHeader = document.getElementById(sortParam);
        if (sortedHeader) {
            tableHeaders.forEach(header => {
                header.classList.remove('sorting_asc', 'sorting_desc');
            });
            sortedHeader.classList.add(`sorting_${directionParam}`);
        }
    }

    tableHeaders.forEach(header => {
        if (header.classList.contains('sorting')) {
            const id = header.getAttribute('id');
            header.addEventListener('click', () => {
                tableHeaders.forEach(otherHeader => {
                    if (otherHeader !== header) {
                        otherHeader.classList.remove('sorting_asc', 'sorting_desc');
                    }
                });

                const isAscending = header.classList.contains('sorting_asc');
                header.classList.toggle('sorting_asc', !isAscending);
                header.classList.toggle('sorting_desc', isAscending);

                const direction = isAscending ? 'desc' : 'asc';
                hitController(`sort=${id}&direction=${direction}`);
            });
        }
    });

    function hitController(sortParameters) {
        const urlObject = new URL(window.location.href);
        const searchParams = new URLSearchParams(urlObject.search);
        sortParameters.split('&').forEach(param => {
            const [key, value] = param.split('=');
            searchParams.set(key, value);
        });
        urlObject.search = searchParams.toString();
        window.location.href = urlObject.toString();
    }
});