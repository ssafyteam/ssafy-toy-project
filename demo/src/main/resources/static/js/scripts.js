/*!
* Start Bootstrap - Blog Post v5.0.9 (https://startbootstrap.com/template/blog-post)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-blog-post/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project
const handleDebounce = (func, wait = 0) => {
    let timeoutID = null;

    return (...args) => {
        clearTimeout(timeoutID);
        timeoutID = setTimeout(() => {
            func(...args);
        }, wait);
    };
};

const onInput = ({target}) => {
    // alert(`Value: ${target.value}`);
    const body = JSON.stringify({previousText : target.value});
    console.log(body)
    fetch('blog/nextline', {
        method: 'POST',
        body,
        headers: {
            "Content-Type": "application/json"
        }
    }).then((res) => {
        if (res.status === 200 || res.status === 201) {
            res.json().then(json => {
                document.querySelector("#recommended_text").textContent
                    = json.choices[0].text.slice(1).trim();
                console.log(json);
            });
        } else {
            console.error(res.statusText);
        }
    }).catch(err => console.error(err));
};
const post_area = document.querySelector('#post-area');
const deboundcedOnInput = handleDebounce(onInput, 500);
post_area.addEventListener('keyup',
    deboundcedOnInput);

const recommended_text = document.querySelector('#recommended_text');
post_area.addEventListener('keydown', (e) => {
    console.log(e.keyCode);
    if (e.keyCode === 9) {
        e.preventDefault();
        console.log("before",post_area.value);
        console.log("rec",recommended_text.textContent);
        post_area.value += " " + recommended_text.textContent;
        console.log("after",post_area.textContent);

    }
})

