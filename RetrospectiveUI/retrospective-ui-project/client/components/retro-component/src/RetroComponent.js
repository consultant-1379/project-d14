/**
 * Component RetroComponent is defined as
 * `<e-retro-component>`
 *
 * Imperatively create component
 * @example
 * let component = new RetroComponent();
 *
 * Declaratively create component
 * @example
 * <e-retro-component></e-retro-component>
 *
 * @extends {LitComponent}
 */
 import { definition } from '@eui/component';
 import { LitComponent, html } from '@eui/lit-component';
 import style from './retroComponent.css';
 
 /**
  * @property {Boolean} propOne - show active/inactive state.
  * @property {String} propTwo - shows the "Hello World" string.
  */
 @definition('e-retro-component', {
   style,
   home: 'retro-component',
   props: {
     retroid: { attribute: true, type: String, default: 'RetroID' },
     complete: { attribute: true, type: Boolean, default: false },
     teamid: { attribute: true, type: String, default: 'TeamID' }
   },
 })
 
 export default class RetroComponent extends LitComponent {
   /**
    * Render the <e-retro-component> component. This function is called each time a
    * prop changes.
    */
   // checks if the retrospective
   checkCompletionStatus(){
     return this.complete ? ["statusCompletePill", "Complete"] : ["statusInProgressPill", "Incomplete"];
   }
 
   render() {
     return html`
       <eui-layout-v0-card slot="example" card-title=${this.retroid} subtitle="Team ID: ${this.teamid}" >
         <div slot="content">
           Current status: 
           <eui-base-v0-pill 
             class=${this.checkCompletionStatus()[0]}>
             ${this.checkCompletionStatus()[1]}
           </eui-base-v0-pill>
         </div>
         <eui-base-v0-tooltip slot="action" message="View retrospective" position="left">
          <a href = "http://localhost:8080/#teamretro-app">
           <eui-v0-icon name="eye" @click=${(event) => {
             console.log('Viewing retrospective', this.retroid);
           }}>
           </eui-v0-icon>
           </a>
         </eui-base-v0-tooltip>
       </eui-layout-v0-card>`;
   }
 }
 
 /**
  * Register the component as e-retro-component.
  * Registration can be done at a later time and with a different name
  */
 RetroComponent.register();
 