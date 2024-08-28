/**
 * Component ProgressVisualizer is defined as
 * `<e-progress-visualizer>`
 *
 * Imperatively create component
 * @example
 * let component = new ProgressVisualizer();
 *
 * Declaratively create component
 * @example
 * <e-progress-visualizer></e-progress-visualizer>
 *
 * @extends {LitComponent}
 */
import { definition } from '@eui/component';
import { LitComponent, html } from '@eui/lit-component';
import style from './progressVisualizer.css';

/**
 * @property {Boolean} propOne - show active/inactive state.
 * @property {String} propTwo - shows the "Hello World" string.
 */
@definition('e-progress-visualizer', {
  style,
  home: 'progress-visualizer',
  props: {
    hideProgressBar: { attribute: true, type: Boolean },
    remaining: { attribute: true, type: Number, default: 0 },
    total: { attribute: true, type: Number, default: 0 }
  },
})
export default class ProgressVisualizer extends LitComponent {
  constructor() {
    super();

    const suffixes = new Map([['one', 'Task'], ['other', 'Tasks']]);

    const pr = new Intl.PluralRules(this.locale);

    this.formatTitle = (n) => {
      const rule = pr.select(n);
      return suffixes.get(rule);
    };
  }
  /**
   * Render the <e-progress-visualizer> component. This function is called each time a
   * prop changes.
   */
  render() {
    const progressPercent = Math.round(
      ((this.total - this.remaining) / this.total) * 100,
      2,
    );
    const progressColor = progressPercent === 100 ? 'green' : 'red';
    const progress = html`<eui-base-v0-progress-bar color=${progressColor} label="Remaining out of ${
      this.total
    }" value=${progressPercent}></eui-base-v0-progress-bar>`;

    return html`
      <label class="simple-counter__remaining">${this.remaining}</label>
      <div class="simple-counter__titles">
        <label 
          class="simple-counter__titles__heading"
        >${this.formatTitle(this.remaining)}</label>
        ${this.hideProgressBar ? nothing : progress}
      </div>`;
  }
}

/**
 * Register the component as e-progress-visualizer.
 * Registration can be done at a later time and with a different name
 */
ProgressVisualizer.register();
