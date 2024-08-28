/**
 * App1 is defined as
 * `<e-app-1>`
 *
 * Imperatively create application
 * @example
 * let app = new App1();
 *
 * Declaratively create application
 * @example
 * <e-app-1></e-app-1>
 *
 * @extends {App}
 */
 import { definition } from '@eui/component';
 import { App, html } from '@eui/app';
 import style from './app1.css';
 import 'person-component';
 import '@eui/base';
 import '@eui/layout';
 
 let username = localStorage.getItem('Username');
 
 
 @definition('e-app-1', {
   style,
   props: {
     response: { attribute: false },
     person: { attribute: false, type: 'list', default: [] },
     name: { attribute: true, type: String, default: ''},
     email: { attribute: true, type: String, default: ''}
   },
 })
 
 
 export default class App1 extends App {
   // Uncomment this block to add initialization code
   constructor() {
     super();
     this.fetchPerson();
     // initialize
   }
 
   createPerson(){
     const body = {
         name: this.name,
         email: username
     };
 
     const response = fetch('http://localhost:9090/person', {
       method: 'POST', 
       body: JSON.stringify(body),
       headers: {'Content-Type': 'application/json'}
     })
     
     window.location.reload();
   
   }
 
 
   fetchPerson(){
       fetch('http://localhost:9090/person/'+username)
       .then((Response => Response.json()))
       .then((jsonRespone) => {
        console.log(jsonRespone)
         let temp = []
         temp = [jsonRespone.email, jsonRespone.name, jsonRespone.teamId]
         this.person = [...temp]
         console.log("User:", this.person)
       })
       .catch((error) => {
         console.log('Error fetching person');
         console.log();
       })
     }
 
   
   render() {      
     
     if(this.person.length > 0){
 
       return html`
         <div>
           <e-person-component
             name=${this.person[1]}
             email=${this.person[0]}
             teamID = ${this.person[2]}>
           </e-person-component>  
         </div>
               `;
     }else{
       return html`
       <div class="container">
       <h1 class="text">Link Your Account Now</h1>
       <br/>
       <div class="fields">
       <eui-base-v0-text-field  name="name" placeholder="Full Name" @change=${(event) => this.name = event.detail.value}/></eui-base-v0-text-field>
       <div></div>
       <eui-base-v0-text-field disabled name="email" placeholder="${username}"></eui-base-v0-text-field>
       </div>
       <br/>
       <eui-base-v0-button class="button" primary
           @click=${(event) =>
             this.createPerson()}>Submit</eui-base-v0-button>
       </div>`;
     }    
     
     
 
   }
   }
 
 /**
  * Register the component as e-app-1.
  * Registration can be done at a later time and with a different name
  * Uncomment the below line to register the App if used outside the container
  */
 // App1.register();
 