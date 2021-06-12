import MenuBar from './MenuBar';
import { shallowToJson } from 'enzyme-to-json';
import { shallow } from 'enzyme';

describe('MenuBar component', () => {

    it("should render the component", () => {
        const wrapper = shallow(<MenuBar />);
        expect(shallowToJson(wrapper)).toMatchSnapshot();
    });
});

