package com.omarstudiolimited.prayertimes.ui.duaa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.omarstudiolimited.prayertimes.MainActivity;
import com.omarstudiolimited.prayertimes.R;

public class DuaaFragment extends Fragment {

    private DuaaboardViewModel duaaboardViewModel;
    View view;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_duaa, container, false);
        TextView toolbarTextView  = (TextView) ((MainActivity) this.getActivity()).findViewById(R.id.tbtv);
        toolbarTextView.setText("ادعية واذكار");

        TextView frag2tv1=(TextView) view.findViewById(R.id.frag2tv1);
        frag2tv1.setText(
                "دعاء الصباح:"+"\n"+
                "اللهم اجعل هذا الصباح صباح خير لا يضيق لنا فيه صدر ولا يخيب لنا فيه أمر، واجعل لنا بكل خطوة توفيق وتيسير وأجر اللهم إني أسألك العفو والعافية في ديني ودنياي وأهلي ومالي، اللهم استر عوراتي وآمن روعاتي، اللهم أحفظني من بين يدي ومن خلفي وعن يميني وعن شمالي ومن فوقي وأعوذ بعظمتك أن أغتال من تحتي"
        );

        TextView frag2tv2=(TextView) view.findViewById(R.id.frag2tv2);
        frag2tv2.setText(
                "دعاء المساء:"+"\n"+
                        "أمسينا وأمسى الملكُ لله والحمد لله، لا إلهَ إلا لله وحده لا شريك له، له الملكُ وله الحمد وهوعلى كلّ شيء قدير، ربِّ أسألك خيرَ ما في هذه الليلة وخيرَ ما بعدَها، وأعوذ بك من شرّ ما في هذه الليلة وشر ما بعدها، ربِّ أعوذُ بك من الكسل وسوء الكبر، ربّ أعوذُ بك من عذابٍ في النار وعذابٍ في القبر"
        );
        TextView frag2tv3=(TextView) view.findViewById(R.id.frag2tv3);
        frag2tv3.setText(
                "دعاء السفر:"+"\n"+
                        "دعاء السفر مكتوب كامل للمسافر بالطائرة أو السيارة أو السفينة أو الدّابة أو أي وسيلة سفر أخرى. فقراءة هذا الدعاء نحتاجها جميعاً عند السفر. وها نحن نضع بين يديك أخي المسلم دعاء السفر مكتوب وكامل وصحيح بإذن الله تعالى ليكون لنا ولك أجرٌه وثوابه في كل سفر وكل رحلة نستودع الله فيها كل شيء"

        );
        TextView frag2tv4=(TextView) view.findViewById(R.id.frag2tv4);
        frag2tv4.setText(
                "دعاء للمريض:"+"\n"+
                        "إلهي أذهب البأس ربّ النّاس، اشف وأنت الشّافي، لا شفاء إلا شفاؤك، شفاءً لا يغادر سقمًا، أذهب البأس ربّ النّاس، بيدك الشّفاء، لا كاشف له إلّا أنت يارب العالمين، اللهم إنّي أسألك من عظيم لطفك وكرمك وسترك الجميل، أن تشفيه وتمدّه بالصحّة والعافية، لا ملجأ ولا منجا منك إلّا إليك، إنّك على كلّ شيءٍ قدير"
        );
        TextView frag2tv5=(TextView) view.findViewById(R.id.frag2tv5);
        frag2tv5.setText(
                "دعاء ختم القرآن الكريم:" +"\n"+
                        "لا إله إلا هو المتوحّد في الجلال بكمال الجمال تعظيماً وتكبيراً، المتفرّد بتصريف الأحوال على التفصيل والإجمال تقديراً وتدبيراً، المتعالي بعظمته ومجده الذي نزلَّ الفرقان على عبده ليكون للعالمين نذيراً وصدق رسوله صلّى الله عليه وسلم الذي أرسله إلى جميع الثقلين، الإنس والجن، بشيراً ونذيراً، وداعياً إلى الله بإذنه وسراجاً منيراً، صدق الله العظيم التوّاب الغفور الوهّاب الذي خضعت لعظمته الرّقاب، وذلّت لجبروته الصّعاب، ولانت لقدرته الشّدائد الصلاب، ربّ الأرباب ومسبّب الأسباب، ومنزّل الكتاب، وخالق خلقه من تراب، غافر الذّنب، وقابل التوبة شديد العقاب، ذو الطول لا إله إلا هو عليه توكّلت وإليه المتاب، صدق من لم يزل جليلاً، صدق حسبي به كفيلاً، صدق من اتّخذته وكيلاً، صدق الهادي إليه سبيلاً، صدق الله ومن أصدق من الله قيلاً، اللهم ارحمني بالقرآن، واجعله لي إماماً ونوراً وهدىً ورحمة، اللهمّ ذكّرني منه ما نسيت، وعلّمني منه ما جهلت، وارزقني تلاوته آناء الليل وأطراف النّهار، واجعله لي حجّةً يا ربّ العالمين، اللهمّ أصلح لي ديني الذي هو عصمة أمري، وأصلح لي دنياي التي فيها معاشي، وأصلح لي آخرتي التي فيها معادي، واجعل الحياة زيادة لي في كلّ خير، واجعل الموت راحة لي من كلّ شر"
        );






        return view;
    }
}