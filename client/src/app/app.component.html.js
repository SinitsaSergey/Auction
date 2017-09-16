({});
{
    title;
}
/h1>
    < button(click);
"switchLanguage('en')" > en < /button>
    < button(click);
"switchLanguage('ru')" > ru < /button>
    < nav >
    !-- * ngIf;
"isLoggedIn()" > -- > Приветствуем;
Вас, welcomeName < /b></p >
    /div>
    < div >
    !-- * ngIf;
"!isLoggedIn()" >
    routerLink;
"/login" > Войти < /span></a >
    routerLink;
"/registration";
routerLinkActive = "active" > Регистрация < /a>-->
    < app - login > /app-login>
    < app - registration > /app-registration>
    < /span>
    < /div>
    < /div>
    < /nav>
    < nav >
    routerLink;
"/admin" > Админка < /span></a >
    routerLink;
"/trading-day" > Аукционы;
сегодня < /span></a >
    routerLink;
"/account" > Личный;
кабинет < /span></a >
    routerLink;
"/login" > Войти < /span></a >
    /nav>
    < div >
    -outlet > /router-outlet>
    < /div>
    < button(click);
"goHome()" > Home < /button>
    < /div>;
