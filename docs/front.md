# client routing

/ dashboard: how many authors, series, books, current books rows?
/books list of books : base book row
/books/:id book entity view
/books/:id/edit book entity edit
/books/new new entity
/books/new?authors=&series=&title=
/authors /:id :id/edit /new (authors -> series -> books row without authors and series)
/series /:id :id/edit /new (series -> book rows without series)

# base book row

title, series, nSeries, Comments (button with number of comments), authors, status, buttons(edit, copy, delete)

# author row

controls (edit, delete - disabled if there are books, addBook with predefined author)

# series row

controls (edit, delete - disabled if there are books, addBook with predefined title and series)

# dataManagement

```js
const store = {
    series: {
        [id]: {
            data: {
                id: id,
                title: string,
                comments: [id],
            },
            syncStatus: number,
        }
    },
    authors: {
        [id]: {
            ['dataLocalCommitted/dataServer']: {
                id: id,
                name: string,
            },
            syncStatus: number,
        }
    },
    books: {
        [id]: {
            ['dataLocalCommitted/dataServer']: {
                id: id,
                title: string,
                authors: [authorId],
                series: id || null,
                seriesN: number || null,
                comments: [id],
            },
            syncStatus: number
        }
    },
    commentsSeries: {
        [id]: {
            ['dataLocalCommitted/dataServer']: {
                title: string,
                series: id,
            },
            syncStatus: number,
        }
    },
    commentsBooks: {
        [id]: {
            ['dataLocalCommitted/dataServer']: {
                title: string,
                book: id,
            },
            syncStatus: number,
        }
    }
}

```

# fetchStatus

localData/serverData: value | null (data not fetched) | undefined (no such entity - deleted)

const ZERO_LENGTH_DIC = {};

l={prop: 1}
s={prop: 2}
f=0
i=4

fetchStatus:
0 - idle
1 - fetching
2 - fetching background
3 - error
4 - error and fetching in background

syncStatus:
0 - no initial info
1 - initial info from local (local storage)
2 - need sync server to local (http)
3 - need sync local to server (update via websockets)
4 - synced

- localEditing - standalone in edit form, fully local state



# engen

https://medium.com/@suraj.kc/mobx-strategies-with-react-hooks-3de23932cb8c
https://idanlevi2.medium.com/react-with-mobx-6-with-persist-and-without-decorators-bd7e5a0578d6

